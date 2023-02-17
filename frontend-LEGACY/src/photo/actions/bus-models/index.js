import {
  BUS_MODELS_REQUEST,
  BUS_MODELS_RECEIVE,
  BUS_MODELS_REJECT,
} from 'photo/actions/types';
import { fetchGet } from 'shared/fetch';

function requestBusModels() {
  return {
    type: BUS_MODELS_REQUEST,
  };
}

function receiveBusModels(response) {
  return {
    type: BUS_MODELS_RECEIVE,
    payload: {
      data: response.data,
    },
  };
}

function rejectBusModels(error) {
  return {
    type: BUS_MODELS_REJECT,
    payload: error,
    error: true,
  };
}

function callFetchBusModels(language) {
  return fetchGet('bus-models', {
    language
  });
}

export default function fetchBusModels(language) {
  return (dispatch, getState) => {
    const state = getState();
    const { isFetching } = state.busModelListReducer;

    if (isFetching) {
      return Promise.resolve();
    }

    dispatch(requestBusModels());
    return callFetchBusModels(language)
      .then(response => dispatch(receiveBusModels(response)))
      .catch(error => dispatch(rejectBusModels(error)));
  };
}
