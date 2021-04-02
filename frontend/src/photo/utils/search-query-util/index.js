const FILTER_ATTRIBUTE_URL_PARAMS = [
  'q',
  'busModelId',
  'busRouteId',
  'fleetPrefix',
  'fleetNumber',
  'licensePlateNumber',
  'routeNumber',
  'username',
];

export default class SearchQueryUtil {
  static convertToQueryParams(filter, sort) {
    const urlQueryParams = new URLSearchParams();
    FILTER_ATTRIBUTE_URL_PARAMS
      .forEach(([filterAttribute, queryParamAttribute]) => {
        if (filter[filterAttribute]) {
          urlQueryParams.append(queryParamAttribute, filter[filterAttribute]);
        }
      });
    return urlQueryParams.toString();
  }
  
  static convertToFilter(queryParams) {
    const urlQueryParams = new URLSearchParams(queryParams);
    return FILTER_ATTRIBUTE_URL_PARAMS.reduce((result, filterAttribute) => ({
      ...result,
      [filterAttribute]: urlQueryParams.get(filterAttribute),
    }), {});
  }

  static convertToApiCallParams(filter, language) {
    const {
      q: query,
      busModelId,
      busRouteId,
      fleetPrefix,
      fleetNumber,
      licensePlateNumber,
      routeNumber,
      username,
    } = filter;
    return {
      search_text: query,
      order_by: 'uploadedDate',
      sort: 'desc',
      language,

      bus_model_id: busModelId,
      bus_route_id: busRouteId,
      bus_route_number: routeNumber,
      fleet_number: fleetNumber,
      fleet_prefix: fleetPrefix,
      license_plate_number: licensePlateNumber,
      uploader_name: username,
    };
  }
}
