export function getPhoto(state) {
  const {
    error,
    isFetching,
    photo,
  } = state.photoDetailReducer;
  return {
    error,
    isFetching,
    photo,
  };
}
