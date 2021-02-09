import {
  PHOTO_DETAIL_REQUEST,
  PHOTO_DETAIL_RECEIVE,
  PHOTO_DETAIL_REJECT,
} from 'photo/actions/types';

const initState = {
  error: '',
  isFetching: false,
  lastUpdated: null,
  photo: null,
};

export default function photoDetailReducer(state = initState, action) {
  switch (action.type) {
    case PHOTO_DETAIL_RECEIVE: {
      const { data } = action.payload;
      const { photo } = data;
      return {
        ...state,
        photo,
        lastUpdated: Date.now(),
        isFetching: false,
      };
    }
    case PHOTO_DETAIL_REJECT:
      return {
        ...state,
        isFetching: false,
        error: action.payload.message,
      };
    case PHOTO_DETAIL_REQUEST:
      return {
        ...state,
        isFetching: true,
        error: '',
      };
    default:
      return state;
  }
}
