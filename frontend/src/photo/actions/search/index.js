import {
  PHOTO_SEARCH_REQUEST,
  PHOTO_SEARCH_RECEIVE,
  PHOTO_SEARCH_REJECT,
} from 'photo/actions/types';
import SearchQueryUtil from 'photo/utils/search-query-util';
import { fetchGet } from 'shared/fetch';

function request(filter, sort, nextSortKey) {
  return {
    type: PHOTO_SEARCH_REQUEST,
    meta: {
      filter,
      sort,
      nextSortKey,
    },
  };
}

function receive(response) {
  return {
    type: PHOTO_SEARCH_RECEIVE,
    payload: {
      data: response.data,
    },
  };
}

function reject(error) {
  return {
    type: PHOTO_SEARCH_REJECT,
    payload: error,
    error: true,
  };
}

function callSearchPhotos(filter, language) {
  return fetchGet('photos', SearchQueryUtil.convertToApiCallParams(filter, language));
}

export default function searchPhotos(
  queryParams,
  sort,
  nextSortKey,
  language,
) {
  return (dispatch, getState) => {
    const state = getState();
    const { isFetching } = state.searchResultReducer;
    if (isFetching) {
      return Promise.resolve();
    }

    const filter = SearchQueryUtil.convertToFilter(queryParams);
    dispatch(request(filter, sort, nextSortKey));
    return callSearchPhotos(filter, language)
      .then(response => dispatch(receive(response)))
      .catch(error => dispatch(reject(error)));
  };
}
