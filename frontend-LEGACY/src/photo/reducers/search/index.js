import isEmpty from 'lodash/isEmpty';

import {
  PHOTO_SEARCH_REQUEST,
  PHOTO_SEARCH_RECEIVE,
  PHOTO_SEARCH_REJECT,
} from 'photo/actions/types';

const initState = {
  error: '',
  filter: {},
  isFetching: false,
  lastUpdated: null,
  lastSortKey: null,
  sort: {
    orderBy: 'uploadedDate',
    sortDirection: 'desc',
  },
  results: [],
  total: 0,
};

export default function searchResultReducer(state = initState, action) {
  switch (action.type) {
    case PHOTO_SEARCH_RECEIVE: {
      const { results: originalResults } = state;
      const { data } = action.payload;
      const {
        total,
        results,
        lastSortKey,
      } = data;
      return {
        ...state,
        isFetching: false,
        lastUpdated: Date.now(),
        lastSortKey,
        results: [
          ...originalResults,
          ...results,
        ],
        total,
      };
    }
    case PHOTO_SEARCH_REJECT:
      return {
        ...state,
        isFetching: false,
        error: action.payload.message,
      };
    case PHOTO_SEARCH_REQUEST:
      const {
        filter,
        sort,
        nextSortKey,
      } = action.meta;
      const shouldClearPreviousResults = isEmpty(nextSortKey);
      return {
        ...state,
        isFetching: true,
        error: '',
        filter,
        sort,
        nextSortKey,
        results: shouldClearPreviousResults ? [] : state.results,
      };
    default:
      return state;
  }
}
