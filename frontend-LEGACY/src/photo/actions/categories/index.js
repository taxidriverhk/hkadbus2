import {
  CATEGORIES_FETCH_REQUEST,
  CATEGORIES_FETCH_RECEIVE,
  CATEGORIES_FETCH_REJECT
} from 'photo/actions/types';
import { fetchGet } from 'shared/fetch';

function request() {
  return {
    type: CATEGORIES_FETCH_REQUEST,
  };
}

function receive(response) {
  return {
    type: CATEGORIES_FETCH_RECEIVE,
    payload: {
      data: response.data,
    },
  };
}

function reject(error) {
  return {
    type: CATEGORIES_FETCH_REJECT,
    payload: error,
    error: true,
  };
}

function callFetchCategories(language) {
  return fetchGet('categories', {
    language
  });
}

export default function fetchCategories(language) {
  return (dispatch, getState) => {
    const state = getState();
    const { isFetching } = state.categoryListReducer;

    if (isFetching) {
      return Promise.resolve();
    }

    dispatch(request());
    return callFetchCategories(language)
      .then(response => dispatch(receive(response)))
      .catch(error => dispatch(reject(error)));
  };
}
