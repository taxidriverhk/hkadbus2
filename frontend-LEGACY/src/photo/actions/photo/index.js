import {
  PHOTO_DETAIL_REQUEST,
  PHOTO_DETAIL_RECEIVE,
  PHOTO_DETAIL_REJECT,
} from 'photo/actions/types';
import { fetchGet } from 'shared/fetch';

function requestPhoto() {
  return {
    type: PHOTO_DETAIL_REQUEST,
  };
}

function receivePhoto(response) {
  return {
    type: PHOTO_DETAIL_RECEIVE,
    payload: {
      data: response.data,
    },
  };
}

function rejectPhoto(error) {
  return {
    type: PHOTO_DETAIL_REJECT,
    payload: error,
    error: true,
  };
}

function callFetchPhoto(photoId, language) {
  return fetchGet(`photos/${photoId}`, {
    language,
  });
}

export default function fetchPhoto(photoId, language) {
  return (dispatch, getState) => {
    const state = getState();
    const { isFetching } = state.photoDetailReducer;

    if (isFetching) {
      return Promise.resolve();
    }

    dispatch(requestPhoto());
    return callFetchPhoto(photoId, language)
      .then(response => dispatch(receivePhoto(response)))
      .catch(error => dispatch(rejectPhoto(error)));
  };
}
