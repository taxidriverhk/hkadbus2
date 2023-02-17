import {
  UPDATES_REQUEST,
  UPDATES_RECEIVE,
  UPDATES_REJECT
} from 'shared/actions/types';

function requestUpdates() {
  return {
    type: UPDATES_REQUEST,
  };
}

function receiveUpdates(response) {
  return {
    type: UPDATES_RECEIVE,
    payload: {
      data: response.data,
    },
  };
}

function rejectUpdates(error) {
  return {
    type: UPDATES_REJECT,
    payload: error,
    error: true,
  };
}

function callFetchUpdates(language) {
  // TODO: call the API once it is ready
  return Promise.resolve({
    data: {
      updates: [],
    },
  });
}

export default function fetchUpdates(language) {
  return (dispatch, getState) => {
    const state = getState();
    const { isFetching } = state.updateListReducer;

    if (isFetching) {
      return Promise.resolve();
    }

    dispatch(requestUpdates());
    return callFetchUpdates(language)
      .then(response => dispatch(receiveUpdates(response)))
      .catch(error => dispatch(rejectUpdates(error)));
  };
}
