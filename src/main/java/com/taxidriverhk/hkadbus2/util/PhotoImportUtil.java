package com.taxidriverhk.hkadbus2.util;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.model.entity.EntityConstants;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.UserEntity;
import com.taxidriverhk.hkadbus2.module.DataAccessModule;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PhotoImportUtil {

    public static enum PhotoImportFileType {
        CSV,
        EXCEL;
    }

    @Builder
    @Data
    private static class PhotoImportEntry {

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

    private static final Integer SHORT_ID_MAX_VALUE = 100000;
    private static final String LANGUAGE_EN = "en_us";
    private static final String LANGUAGE_ZH = "zh_hk";
    private static final String INPUT_FILE_PATH = "C:\\Users\\Taxi Driver\\Downloads\\hkadbus2-import.csv";

    private final SessionFactory sessionFactory;
    private final Set<Long> usedShortIds;

    public PhotoImportUtil() {
        final Injector injector = Guice.createInjector(new DataAccessModule());
        this.sessionFactory = injector.getInstance(SessionFactory.class);
        this.usedShortIds = Sets.newHashSet();
    }

    @VisibleForTesting
    public PhotoImportUtil(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.usedShortIds = Sets.newHashSet();
    }

    public static void main(final String[] args) throws Exception {
        final PhotoImportUtil importUtil = new PhotoImportUtil();
        importUtil.execute();
    }

    public void execute() throws Exception {
        execute(INPUT_FILE_PATH, PhotoImportFileType.CSV);
    }

    @VisibleForTesting
    public List<PhotoEntity> execute(final String inputFilePath, final PhotoImportFileType fileType) throws Exception {
        final List<PhotoImportEntry> photoImportEntries = fileType == PhotoImportFileType.CSV
            ? readFromCsv(inputFilePath)
            : readFromExcel(inputFilePath);

        final Session session = sessionFactory.openSession();
        // Insert categories (keyed by category hash key)
        final Transaction insertCategoryTransaction = session.beginTransaction();
        final Map<String, CategoryEntity> categoryHashKeyToCategoryEntityMap = photoImportEntries.stream()
                .collect(Collectors.toMap(PhotoImportEntry::getCategoryHashKey, Function.identity(), (c1, c2) -> c1))
                .values()
                .stream()
                .map(photoImportEntry -> constructEntityAndSave(
                        session,
                        CategoryEntity.builder()
                                .hashKey(photoImportEntry.getCategoryHashKey())
                                .name(ImmutableMap.of(
                                        LANGUAGE_EN, photoImportEntry.getCategoryNameEn(),
                                        LANGUAGE_ZH, photoImportEntry.getCategoryNameZh()))
                                .thumbnail(photoImportEntry.getThumbnail())
                                .build(),
                        CategoryEntity.class,
                        tryGetExistingEntity(
                                session,
                                CategoryEntity.class,
                                EntityConstants.HASH_KEY_ATTRIBUTE,
                                photoImportEntry.getCategoryHashKey())))
                .collect(Collectors.toMap(CategoryEntity::getHashKey, Function.identity()));
        insertCategoryTransaction.commit();

        // Insert advertisements (keyed by advertisement hash key)
        final Transaction insertAdvertisementTransaction = session.beginTransaction();
        final Map<String, AdvertisementEntity> advertisementHashKeyToAdvertisementEntityMap = photoImportEntries
                .stream()
                .collect(Collectors.toMap(PhotoImportEntry::getAdvertisementHashKey, Function.identity(),
                        (a1, a2) -> a1))
                .values()
                .stream()
                .map(photoImportEntry -> constructEntityAndSave(
                        session,
                        AdvertisementEntity.builder()
                                .hashKey(photoImportEntry.getAdvertisementHashKey())
                                .name(ImmutableMap.of(
                                        LANGUAGE_EN, photoImportEntry.getAdvertisementNameEn(),
                                        LANGUAGE_ZH, photoImportEntry.getAdvertisementNameZh()))
                                .thumbnail(photoImportEntry.getThumbnail())
                                .category(categoryHashKeyToCategoryEntityMap.get(photoImportEntry.getCategoryHashKey()))
                                .build(),
                        AdvertisementEntity.class,
                        tryGetExistingEntity(
                                session,
                                AdvertisementEntity.class,
                                EntityConstants.HASH_KEY_ATTRIBUTE,
                                photoImportEntry.getAdvertisementHashKey())))
                .collect(Collectors.toMap(AdvertisementEntity::getHashKey, Function.identity()));
        insertAdvertisementTransaction.commit();

        // Insert users (keyed by username)
        final Transaction insertUserTransaction = session.beginTransaction();
        final Map<String, UserEntity> usernameToUserEntityMap = photoImportEntries.stream()
                .collect(Collectors.toMap(PhotoImportEntry::getUsername, Function.identity(), (u1, u2) -> u1))
                .values()
                .stream()
                .map(photoImportEntry -> constructEntityAndSave(
                        session,
                        UserEntity.builder()
                                .username(photoImportEntry.getUsername())
                                .passwordHash("placeholder-hash")
                                .group("user")
                                .registrationDate(Date.from(Instant.now()))
                                .lastLoggedInDate(Date.from(Instant.now()))
                                .build(),
                        UserEntity.class,
                        tryGetExistingEntity(
                                session,
                                UserEntity.class,
                                "username",
                                photoImportEntry.getUsername())))
                .collect(Collectors.toMap(UserEntity::getUsername, Function.identity()));
        insertUserTransaction.commit();

        // Insert bus brands (keyed by bus brand hash key)
        final Transaction insertBusBrandTransaction = session.beginTransaction();
        final Map<String, BusBrandEntity> busBrandHashKeyToBusBrandEntityMap = photoImportEntries.stream()
                .collect(Collectors.toMap(PhotoImportEntry::getBusBrandHashKey, Function.identity(), (b1, b2) -> b1))
                .values()
                .stream()
                .map(photoImportEntry -> constructEntityAndSave(
                        session,
                        BusBrandEntity.builder()
                                .hashKey(photoImportEntry.getBusBrandHashKey())
                                .name(ImmutableMap.of(
                                        LANGUAGE_EN, photoImportEntry.getBusBrandNameEn(),
                                        LANGUAGE_ZH, photoImportEntry.getBusBrandNameZh()))
                                .build(),
                        BusBrandEntity.class,
                        tryGetExistingEntity(
                                session,
                                BusBrandEntity.class,
                                EntityConstants.HASH_KEY_ATTRIBUTE,
                                photoImportEntry.getBusBrandHashKey())))
                .collect(Collectors.toMap(BusBrandEntity::getHashKey, Function.identity()));
        insertBusBrandTransaction.commit();

        // Insert bus models (keyed by bus model hash key)
        final Transaction insertBusModelTransaction = session.beginTransaction();
        final Map<String, BusModelEntity> busModelHashKeyToBusModelEntityMap = photoImportEntries.stream()
                .collect(Collectors.toMap(PhotoImportEntry::getBusModelHashKey, Function.identity(), (b1, b2) -> b1))
                .values()
                .stream()
                .map(photoImportEntry -> constructEntityAndSave(
                        session,
                        BusModelEntity.builder()
                                .hashKey(photoImportEntry.getBusModelHashKey())
                                .name(ImmutableMap.of(
                                        LANGUAGE_EN, photoImportEntry.getBusModelNameEn(),
                                        LANGUAGE_ZH, photoImportEntry.getBusModelNameZh()))
                                .busBrand(busBrandHashKeyToBusBrandEntityMap.get(photoImportEntry.getBusBrandHashKey()))
                                .thumbnail(photoImportEntry.getThumbnail())
                                .build(),
                        BusModelEntity.class,
                        tryGetExistingEntity(
                                session,
                                BusModelEntity.class,
                                EntityConstants.HASH_KEY_ATTRIBUTE,
                                photoImportEntry.getBusModelHashKey())))
                .collect(Collectors.toMap(BusModelEntity::getHashKey, Function.identity()));
        insertBusModelTransaction.commit();

        // Insert buses (keyed by license plate number)
        final Transaction insertBusTransactions = session.beginTransaction();
        final Map<String, BusEntity> licensePlateToBusEntityMap = photoImportEntries.stream()
                .collect(Collectors.toMap(PhotoImportEntry::getLicensePlateNumber, Function.identity(), (b1, b2) -> b1))
                .values()
                .stream()
                .map(photoImportEntry -> constructEntityAndSave(
                        session,
                        BusEntity.builder()
                                .licensePlateNumber(photoImportEntry.getLicensePlateNumber())
                                .busCompany(photoImportEntry.getBusCompany())
                                .fleetPrefix(photoImportEntry.getFleetPrefix())
                                .fleetNumber(photoImportEntry.getFleetNumber())
                                .busModel(busModelHashKeyToBusModelEntityMap.get(photoImportEntry.getBusModelHashKey()))
                                .build(),
                        BusEntity.class,
                        tryGetExistingEntity(
                                session,
                                BusEntity.class,
                                "licensePlateNumber",
                                photoImportEntry.getLicensePlateNumber())))
                .collect(Collectors.toMap(BusEntity::getLicensePlateNumber, Function.identity()));
        insertBusTransactions.commit();

        // Insert bus routes (keyed by route hash key)
        final Transaction insertBusRouteTransactions = session.beginTransaction();
        final Map<String, BusRouteEntity> busRouteHashKeyToBusRouteEntityMap = photoImportEntries.stream()
                .collect(Collectors.toMap(PhotoImportEntry::getBusRouteHashKey, Function.identity(), (b1, b2) -> b1))
                .values()
                .stream()
                .map(photoImportEntry -> constructEntityAndSave(
                        session,
                        BusRouteEntity.builder()
                                .hashKey(photoImportEntry.getBusRouteHashKey())
                                .routeNumber(photoImportEntry.getBusRouteNumber())
                                .busCompanies(Arrays.asList(photoImportEntry.getBusRouteCompanies()))
                                .startLocation(ImmutableMap.of(
                                        LANGUAGE_EN, photoImportEntry.getStartEn(),
                                        LANGUAGE_ZH, photoImportEntry.getStartZh()))
                                .endLocation(ImmutableMap.of(
                                        LANGUAGE_EN, photoImportEntry.getEndEn(),
                                        LANGUAGE_ZH, photoImportEntry.getEndZh()))
                                .build(),
                        BusRouteEntity.class,
                        tryGetExistingEntity(
                                session,
                                BusRouteEntity.class,
                                EntityConstants.HASH_KEY_ATTRIBUTE,
                                photoImportEntry.getBusRouteHashKey())))
                .collect(Collectors.toMap(BusRouteEntity::getHashKey, Function.identity()));
        insertBusRouteTransactions.commit();

        // Insert photos
        final Transaction insertPhotoTransactions = session.beginTransaction();
        final List<PhotoEntity> photoEntities = photoImportEntries.stream()
                .map(photoImportEntry -> constructEntityAndSave(
                        session,
                        PhotoEntity.builder()
                                .shortId(generateShortId())
                                .advertisement(advertisementHashKeyToAdvertisementEntityMap
                                        .get(photoImportEntry.getAdvertisementHashKey()))
                                .bus(licensePlateToBusEntityMap.get(photoImportEntry.getLicensePlateNumber()))
                                .busRoute(busRouteHashKeyToBusRouteEntityMap.get(photoImportEntry.getBusRouteHashKey()))
                                .user(usernameToUserEntityMap.get(photoImportEntry.getUsername()))
                                .uploadedDate(Date.from(Instant.now()))
                                .image(photoImportEntry.getImage())
                                .thumbnail(photoImportEntry.getThumbnail())
                                .build(),
                        PhotoEntity.class,
                        Optional.empty()))
                .collect(Collectors.toList());
        insertPhotoTransactions.commit();

        // Insert search records
        final Transaction insertSearchRecordTransactions = session.beginTransaction();
        for (int i = 0; i < photoImportEntries.size(); i++) {
            final PhotoImportEntry photoImportEntry = photoImportEntries.get(i);
            session.save(SearchRecordEntity.builder()
                    .photoShortId(photoEntities.get(i).getShortId())
                    .language(LANGUAGE_EN)
                    .categoryHashKey(photoImportEntry.getCategoryHashKey())
                    .categoryName(photoImportEntry.getCategoryNameEn())
                    .advertisementHashKey(photoImportEntry.getAdvertisementHashKey())
                    .advertisementName(photoImportEntry.getAdvertisementNameEn())
                    .busModelHashKey(photoImportEntry.getBusModelHashKey())
                    .busModelName(photoImportEntry.getBusModelNameEn())
                    .busCompany(photoImportEntry.getBusCompany())
                    .routeHashKey(photoImportEntry.getBusRouteHashKey())
                    .routeNumber(photoImportEntry.getBusRouteNumber())
                    .fleetPrefix(photoImportEntry.getFleetPrefix())
                    .fleetNumber(photoImportEntry.getFleetNumber())
                    .licensePlateNumber(photoImportEntry.getLicensePlateNumber())
                    .uploadedDate(System.currentTimeMillis())
                    .username(photoImportEntry.getUsername())
                    .thumbnail(photoImportEntry.getThumbnail())
                    .tags(generateTags(photoImportEntry))
                    .build());
            session.save(SearchRecordEntity.builder()
                    .photoShortId(photoEntities.get(i).getShortId())
                    .language(LANGUAGE_ZH)
                    .categoryHashKey(photoImportEntry.getCategoryHashKey())
                    .categoryName(photoImportEntry.getCategoryNameZh())
                    .advertisementHashKey(photoImportEntry.getAdvertisementHashKey())
                    .advertisementName(photoImportEntry.getAdvertisementNameZh())
                    .busModelHashKey(photoImportEntry.getBusModelHashKey())
                    .busModelName(photoImportEntry.getBusModelNameZh())
                    .busCompany(photoImportEntry.getBusCompany())
                    .routeHashKey(photoImportEntry.getBusRouteHashKey())
                    .routeNumber(photoImportEntry.getBusRouteNumber())
                    .fleetPrefix(photoImportEntry.getFleetPrefix())
                    .fleetNumber(photoImportEntry.getFleetNumber())
                    .licensePlateNumber(photoImportEntry.getLicensePlateNumber())
                    .uploadedDate(System.currentTimeMillis())
                    .username(photoImportEntry.getUsername())
                    .thumbnail(photoImportEntry.getThumbnail())
                    .tags(generateTags(photoImportEntry))
                    .build());
        }
        insertSearchRecordTransactions.commit();

        return photoEntities;
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
                    .categoryNameEn(getStringExcelCellValue(row, 0))
                    .categoryNameZh(getStringExcelCellValue(row, 1))
                    .categoryHashKey(getStringExcelCellValue(row, 2))
                    .advertisementNameEn(getStringExcelCellValue(row, 3))
                    .advertisementNameZh(getStringExcelCellValue(row, 4))
                    .advertisementHashKey(getStringExcelCellValue(row, 5))
                    .busBrandNameEn(getStringExcelCellValue(row, 6))
                    .busBrandNameZh(getStringExcelCellValue(row, 7))
                    .busBrandHashKey(getStringExcelCellValue(row, 8))
                    .busModelNameEn(getStringExcelCellValue(row, 9))
                    .busModelNameZh(getStringExcelCellValue(row, 10))
                    .busModelHashKey(getStringExcelCellValue(row, 11))
                    .licensePlateNumber(getStringExcelCellValue(row, 12))
                    .fleetPrefix(getStringExcelCellValue(row, 13))
                    .fleetNumber(getStringExcelCellValue(row, 14))
                    .busCompany(getStringExcelCellValue(row, 15))
                    .busRouteHashKey(getStringExcelCellValue(row, 16))
                    .busRouteNumber(getStringExcelCellValue(row, 17))
                    .busRouteCompanies(getStringExcelCellValue(row, 18).split("\\|"))
                    .startEn(getStringExcelCellValue(row, 19))
                    .startZh(getStringExcelCellValue(row, 20))
                    .endEn(getStringExcelCellValue(row, 21))
                    .endZh(getStringExcelCellValue(row, 22))
                    .username(getStringExcelCellValue(row, 23))
                    .thumbnail(getStringExcelCellValue(row, 24))
                    .image(getStringExcelCellValue(row, 25))
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

    @SneakyThrows
    private <T, V> Optional<T> tryGetExistingEntity(
            final Session session,
            final Class<T> entityClass,
            final String attribute,
            final V value
    ) {
        return SqlQueryUtil.selectSingleItemByCompositeKey(
                session,
                entityClass,
                attribute,
                value);
    }

    @SneakyThrows
    private <T> T constructEntityAndSave(
            final Session session,
            final T entity,
            final Class<T> entityType,
            final Optional<T> existingEntity
    ) {
        if (existingEntity.isPresent()) {
            return existingEntity.get();
        }

        final UUID id = (UUID) session.save(entity);
        final Method setIdMethod = entityType.getMethod("setId", UUID.class);
        setIdMethod.invoke(entity, id);
        return entity;
    }

    private Long generateShortId() {
        final Random random = new Random();
        Long shortId = random.nextInt(SHORT_ID_MAX_VALUE) + 1L;
        while (usedShortIds.contains(shortId)) {
            usedShortIds.add(shortId);
            shortId = random.nextInt(SHORT_ID_MAX_VALUE) + 1L;
        }
        return shortId;
    }

    private String generateTags(final PhotoImportEntry photoImportEntry) {
        final Set<String> tags = new HashSet<String>();
        tags.addAll(lowerCaseAndSplitBySpace(photoImportEntry.getAdvertisementNameEn()));
        tags.add(photoImportEntry.getAdvertisementNameZh());
        tags.addAll(lowerCaseAndSplitBySpace(photoImportEntry.getCategoryNameEn()));
        tags.add(photoImportEntry.getCategoryNameZh());
        tags.addAll(lowerCaseAndSplitBySpace(photoImportEntry.getBusBrandNameEn()));
        tags.add(photoImportEntry.getBusBrandNameZh());
        tags.add(photoImportEntry.getBusCompany());
        tags.addAll(lowerCaseAndSplitBySpace(photoImportEntry.getBusModelNameEn()));
        tags.add(photoImportEntry.getBusModelNameZh());
        tags.add(photoImportEntry.getBusRouteNumber().toLowerCase());
        tags.add(photoImportEntry.getFleetPrefix().toLowerCase());
        tags.add(photoImportEntry.getFleetPrefix().toLowerCase() + photoImportEntry.getFleetNumber());
        tags.add(photoImportEntry.getUsername().toLowerCase());

        return String.join(",", tags);
    }

    private List<String> lowerCaseAndSplitBySpace(String input) {
        return Arrays.asList(input.toLowerCase().split(" "));
    }
}
