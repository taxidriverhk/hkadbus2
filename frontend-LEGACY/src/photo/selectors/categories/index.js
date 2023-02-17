export function getCategory(state) {
  const { category } = state.advertisementListReducer;
  return category || {};
}

export function getCategories(state) {
  const {
    categories,
    error,
    isFetching,
  } = state.categoryListReducer;
  return {
    categories,
    error,
    isFetching,
  };
}
