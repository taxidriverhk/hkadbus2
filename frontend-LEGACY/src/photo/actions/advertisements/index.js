import {
  ADVERTISEMENTS_FETCH_REQUEST,
  ADVERTISEMENTS_FETCH_RECEIVE,
  ADVERTISEMENTS_FETCH_REJECT
} from 'photo/actions/types';
import { fetchGet } from 'shared/fetch';

function request() {
  return {
    type: ADVERTISEMENTS_FETCH_REQUEST,
  };
}

function receive(categoryId, response) {
  return {
    type: ADVERTISEMENTS_FETCH_RECEIVE,
    payload: {
      data: {
        ...response.data,
        categoryId,
      },
    },
  };
}

function reject(error) {
  return {
    type: ADVERTISEMENTS_FETCH_REJECT,
    payload: error,
    error: true,
  };
}

function callFetchAdvertisements(categoryId, language) {
  return fetchGet(`categories/${categoryId}/advertisements`, {
    language,
  });
}

export default function fetchAdvertisements(categoryId, language) {
  return (dispatch, getState) => {
    const state = getState();
    const { isFetching } = state.advertisementListReducer;

    if (isFetching) {
      return Promise.resolve();
    }

    dispatch(request());
    return callFetchAdvertisements(categoryId, language)
      .then(response => dispatch(receive(categoryId, response)))
      .catch(error => dispatch(reject(error)));
  };
}
