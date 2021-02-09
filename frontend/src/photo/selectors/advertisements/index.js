import get from 'lodash/get';

export function getAdvertisements(state) {
  const {
    category,
    advertisements: advertisementSet,
    isFetching,
    error,
  } = state.advertisementListReducer;

  const categoryId = get(category, 'id', 0);
  const advertisements = get(advertisementSet, categoryId, []);
  return {
    advertisements,
    category,
    isFetching,
    error,
  };
}
