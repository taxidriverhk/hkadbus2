import get from 'lodash/get';
import {
  ADVERTISEMENTS_FETCH_REQUEST,
  ADVERTISEMENTS_FETCH_RECEIVE,
  ADVERTISEMENTS_FETCH_REJECT
} from 'photo/actions/types';

const initState = {
  error: '',
  isFetching: false,
  lastUpdated: null,
  category: null,
  advertisements: {},
};

export default function advertisementListReducer(state = initState, action) {
  switch (action.type) {
    case ADVERTISEMENTS_FETCH_RECEIVE: {
      const { data } = action.payload;
      const {
        categoryId,
        advertisements
      } = data;
      const categoryName = get(advertisements, '[0].categoryName', null);

      return {
        ...state,
        category: categoryName ? {
          id: categoryId,
          name: categoryName
        } : null,
        advertisements: {
          ...state.advertisements,
          [categoryId]: advertisements,
        },
        lastUpdated: Date.now(),
        isFetching: false,
      };
    }
    case ADVERTISEMENTS_FETCH_REJECT:
      return {
        ...state,
        isFetching: false,
        error: action.payload.message,
      };
    case ADVERTISEMENTS_FETCH_REQUEST:
      return {
        ...state,
        isFetching: true,
        error: '',
      };
    default:
      return state;
  }
}
