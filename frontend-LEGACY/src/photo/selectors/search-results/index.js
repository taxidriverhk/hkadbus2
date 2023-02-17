export function getSearchResults(state) {
  const {
    error,
    filter,
    isFetching,
    lastSortKey,
    sort,
    results,
  } = state.searchResultReducer;
  return {
    error,
    filter,
    isFetching,
    lastSortKey,
    sort,
    results,
  };
}
