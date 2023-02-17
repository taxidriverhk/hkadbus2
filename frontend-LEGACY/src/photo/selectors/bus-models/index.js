export function getBusModels(state) {
  const {
    busBrandModelMap,
    error,
    isFetching,
  } = state.busModelListReducer;
  return {
    busBrandModelMap,
    error,
    isFetching,
  };
}
