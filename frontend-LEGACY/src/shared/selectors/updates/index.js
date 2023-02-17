export const getUpdates = (state) => {
  const {
    error,
    isFetching,
    updates,
  } = state.updateListReducer;
  return {
    error,
    isFetching,
    updates,
  };
};
