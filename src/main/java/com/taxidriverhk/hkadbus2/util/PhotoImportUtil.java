package com.taxidriverhk.hkadbus2.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.taxidriverhk.hkadbus2.activity.auth.Authenticator;
import com.taxidriverhk.hkadbus2.model.api.PutPhotoRequest;
import com.taxidriverhk.hkadbus2.model.domain.BusCompany;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

public class PhotoImportUtil {

    public static enum PhotoImportFileType {
        CSV,
        EXCEL;
    }

    @Builder
    @Data
    private static class PhotoImportEntry {

        private final boolean inserted;
        private final int rowNumber;
        private final String categoryNameEn;
        private final String categoryNameZh;
        private final String categoryHashKey;
        private final String advertisementNameEn;
        private final String advertisementNameZh;
        private final String advertisementHashKey;
        private final String busBrandNameEn;
        private final String busBrandNameZh;
        private final String busBrandHashKey;
        private final String busModelNameEn;
        private final String busModelNameZh;
        private final String busModelHashKey;
        private final String licensePlateNumber;
        private final String fleetPrefix;
        private final String fleetNumber;
        private final String busCompany;
        private final String busRouteHashKey;
        private final String busRouteNumber;
        private final String[] busRouteCompanies;
        private final String startEn;
        private final String startZh;
        private final String endEn;
        private final String endZh;
        private final String username;
        private final String thumbnail;
        private final String image;
        private final String tags;
    }

    private static final String LANGUAGE_EN = "en_us";
    private static final String LANGUAGE_ZH = "zh_hk";

    // Change the path to the CSV/Excel file to begin the import
    private static final String INPUT_FILE_PATH = "<placeholder-file-path>";
    // Change this to point to a different endpoint if needed
    private static final String API_URL = "http://localhost:8080/hkadbus2/api/photo";
    // Provide the API key required to make the put call
    // If null, then one will attempt to be generated
    private static final String API_KEY = null;

    private final Gson gson;
    private final CloseableHttpClient httpClient;
    private final Authenticator authenticator;

    public PhotoImportUtil() {
        gson = new Gson();
        httpClient = HttpClientBuilder.create().build();

        final String secret = System.getenv("ENCRYPTOR_PASSWORD");
        final Algorithm authTokenSigner = Algorithm.HMAC256(secret);
        authenticator = new Authenticator(authTokenSigner);
    }

    @VisibleForTesting
    public PhotoImportUtil(final CloseableHttpClient httpClient, final Authenticator authenticator) {
        this.gson = new Gson();
        this.httpClient = httpClient;
        this.authenticator = authenticator;
    }

    public static void main(final String[] args) throws Exception {
        final PhotoImportUtil importUtil = new PhotoImportUtil();
        importUtil.execute();
    }

    public void execute() throws Exception {
        final PhotoImportFileType fileType = INPUT_FILE_PATH.endsWith(".csv") ? PhotoImportFileType.CSV : PhotoImportFileType.EXCEL;
        execute(INPUT_FILE_PATH, fileType);
    }

    @VisibleForTesting
    public void execute(final String inputFilePath, final PhotoImportFileType fileType) throws Exception {
        String apiKey = API_KEY;
        if (apiKey == null) {
            apiKey = authenticator.generateToken();
        }

        final List<PhotoImportEntry> photoImportEntries = fileType == PhotoImportFileType.CSV
            ? readFromCsv(inputFilePath)
            : readFromExcel(inputFilePath);

        // Using a for loop to allow easier debugging and to ensure the requests are done serially
        for (final PhotoImportEntry photoImportEntry : photoImportEntries) {
            if (photoImportEntry.isInserted()) {
                continue;
            }

            final PutPhotoRequest putPhotoRequest = PutPhotoRequest.builder()
                    .advertisementId(photoImportEntry.getAdvertisementHashKey())
                    .advertisementNames(ImmutableMap.of(
                            LANGUAGE_EN, photoImportEntry.getAdvertisementNameEn(),
                            LANGUAGE_ZH, photoImportEntry.getAdvertisementNameZh()))
                    .busBrandId(photoImportEntry.getBusBrandHashKey())
                    .busBrandNames(ImmutableMap.of(
                            LANGUAGE_EN, photoImportEntry.getBusBrandNameEn(),
                            LANGUAGE_ZH, photoImportEntry.getBusBrandNameZh()))
                    .busCompany(BusCompany.fromString(photoImportEntry.getBusCompany()))
                    .busModelId(photoImportEntry.getBusModelHashKey())
                    .busModelNames(ImmutableMap.of(
                            LANGUAGE_EN, photoImportEntry.getBusModelNameEn(),
                            LANGUAGE_ZH, photoImportEntry.getBusModelNameZh()))
                    .busRouteEndLocationNames(ImmutableMap.of(
                            LANGUAGE_EN, photoImportEntry.getEndEn(),
                            LANGUAGE_ZH, photoImportEntry.getEndZh()))
                    .busRouteId(photoImportEntry.getBusRouteHashKey())
                    .busRouteStartLocationNames(ImmutableMap.of(
                            LANGUAGE_EN, photoImportEntry.getStartEn(),
                            LANGUAGE_ZH, photoImportEntry.getStartZh()))
                    .categoryId(photoImportEntry.getCategoryHashKey())
                    .categoryNames(ImmutableMap.of(
                            LANGUAGE_EN, photoImportEntry.getCategoryNameEn(),
                            LANGUAGE_ZH, photoImportEntry.getCategoryNameZh()))
                    .fleetNumber(photoImportEntry.getFleetNumber())
                    .fleetPrefix(photoImportEntry.getFleetPrefix())
                    .image(photoImportEntry.getImage())
                    .licensePlateNumber(photoImportEntry.getLicensePlateNumber())
                    .routeNumber(photoImportEntry.getBusRouteNumber())
                    .thumbnail(photoImportEntry.getThumbnail())
                    .username(photoImportEntry.getUsername())
                    .skipInsertionWithSameThumbnail(true)
                    .build();

            final HttpPost request = new HttpPost(API_URL);
            final StringEntity payload = new StringEntity(gson.toJson(putPhotoRequest));

            request.setEntity(payload);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Authorization", apiKey);

            try (final CloseableHttpResponse response = httpClient.execute(request)) {
                final String responseStr = EntityUtils.toString(response.getEntity());
                System.out.println(
                    String.format("Inserted photo at row %d with result %s", photoImportEntry.getRowNumber(), responseStr));
            }
        }

        httpClient.close();
    }

    @SneakyThrows
    private List<PhotoImportEntry> readFromCsv(final String inputFilePath) {
        // Read csv file with data
        // Each row has the following attributes
        // [
        //      category_name_en,category_name_zh,category_hash_key,advertisement_name_en,advertisement_name_zh,advertisement_hash_key,
        //      bus_brand_name_en,bus_brand_name_zh,bus_brand_hash_key,bus_model_name_en,bus_model_name_zh,bus_model_hash_key,
        //      license_plate_number,fleet_prefix,fleet_number,bus_company,
        //      bus_route_hash_key,bus_route_number,bus_companies,start_en,start_zh,end_en,end_zh,
        //      username,
        //      thumbnail,image,
        // ]
        final List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
        // Remove the header line
        lines.remove(0);
        return lines.stream()
            .map(line -> {
                final String[] tokens = line.split(",");
                return PhotoImportEntry.builder()
                        .categoryNameEn(tokens[0])
                        .categoryNameZh(tokens[1])
                        .categoryHashKey(tokens[2])
                        .advertisementNameEn(tokens[3])
                        .advertisementNameZh(tokens[4])
                        .advertisementHashKey(tokens[5])
                        .busBrandNameEn(tokens[6])
                        .busBrandNameZh(tokens[7])
                        .busBrandHashKey(tokens[8])
                        .busModelNameEn(tokens[9])
                        .busModelNameZh(tokens[10])
                        .busModelHashKey(tokens[11])
                        .licensePlateNumber(tokens[12])
                        .fleetPrefix(tokens[13])
                        .fleetNumber(tokens[14])
                        .busCompany(tokens[15])
                        .busRouteHashKey(tokens[16])
                        .busRouteNumber(tokens[17])
                        .busRouteCompanies(tokens[18].split("\\|"))
                        .startEn(tokens[19])
                        .startZh(tokens[20])
                        .endEn(tokens[21])
                        .endZh(tokens[22])
                        .username(tokens[23])
                        .thumbnail(tokens[24])
                        .image(tokens[25])
                        .build();
            })
            .collect(Collectors.toList());
    }

    @SneakyThrows
    private List<PhotoImportEntry> readFromExcel(final String inputFilePath) {
        final FileInputStream excelFile = new FileInputStream(new File(inputFilePath));
        final Workbook workbook = new XSSFWorkbook(excelFile);

        final Sheet sheet = workbook.getSheetAt(0);
        final List<PhotoImportEntry> entries = Lists.newLinkedList();
        for (final Row row : sheet) {
            // Skip the header
            if (row.getRowNum() == 0) {
                continue;
            }

            final PhotoImportEntry entry = PhotoImportEntry.builder()
                    .inserted("Y".equals(getStringExcelCellValue(row, 0)))
                    .rowNumber(row.getRowNum() + 1)
                    .categoryNameEn(getStringExcelCellValue(row, 1))
                    .categoryNameZh(getStringExcelCellValue(row, 2))
                    .categoryHashKey(getStringExcelCellValue(row, 3))
                    .advertisementNameEn(getStringExcelCellValue(row, 4))
                    .advertisementNameZh(getStringExcelCellValue(row, 5))
                    .advertisementHashKey(getStringExcelCellValue(row, 6))
                    .busBrandNameEn(getStringExcelCellValue(row, 7))
                    .busBrandNameZh(getStringExcelCellValue(row,8))
                    .busBrandHashKey(getStringExcelCellValue(row, 9))
                    .busModelNameEn(getStringExcelCellValue(row, 10))
                    .busModelNameZh(getStringExcelCellValue(row, 11))
                    .busModelHashKey(getStringExcelCellValue(row, 12))
                    .licensePlateNumber(getStringExcelCellValue(row, 13))
                    .fleetPrefix(getStringExcelCellValue(row, 14))
                    .fleetNumber(getStringExcelCellValue(row, 15))
                    .busCompany(getStringExcelCellValue(row, 16))
                    .busRouteHashKey(getStringExcelCellValue(row, 17))
                    .busRouteNumber(getStringExcelCellValue(row, 18))
                    .busRouteCompanies(getStringExcelCellValue(row, 19).split("\\|"))
                    .startEn(getStringExcelCellValue(row, 20))
                    .startZh(getStringExcelCellValue(row, 21))
                    .endEn(getStringExcelCellValue(row, 22))
                    .endZh(getStringExcelCellValue(row, 23))
                    .username(getStringExcelCellValue(row, 24))
                    .thumbnail(getStringExcelCellValue(row, 25))
                    .image(getStringExcelCellValue(row, 26))
                    .build();
            entries.add(entry);
        }

        workbook.close();
        return entries;
    }

    private String getStringExcelCellValue(final Row row, final int index) {
        final Cell cell = row.getCell(index);
        if (cell.getCellType() == CellType.NUMERIC) {
            return new Integer((int) cell.getNumericCellValue()).toString();
        }

        return row.getCell(index).getStringCellValue();
    }
}
