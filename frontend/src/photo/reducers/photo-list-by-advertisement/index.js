import get from 'lodash/get';
import {
  PHOTO_LIST_BY_ADVERTISEMENT_REQUEST,
  PHOTO_LIST_BY_ADVERTISEMENT_RECEIVE,
  PHOTO_LIST_BY_ADVERTISEMENT_REJECT,
} from 'photo/actions/types';

const initState = {
  error: '',
  isFetching: false,
  lastUpdated: null,
  advertisement: null,
  category: null,
  photos: {},
};

export default function photoListByAdvertisementReducer(state = initState, action) {
  switch (action.type) {
    case PHOTO_LIST_BY_ADVERTISEMENT_RECEIVE: {
      const { data } = action.payload;
      const { photos } = data;
      const advertisement = get(photos, '[0].advertisement', null);
      const category = get(photos, '[0].advertisement.category', null);

      return {
        ...state,
        advertisement,
        category,
        photos: {
          ...state.photos,
          [advertisement.id]: [
            ...photos,
          ],
        },
        lastUpdated: Date.now(),
        isFetching: false,
      };
    }
    case PHOTO_LIST_BY_ADVERTISEMENT_REJECT:
      return {
        ...state,
        isFetching: false,
        error: action.payload.message,
      };
    case PHOTO_LIST_BY_ADVERTISEMENT_REQUEST:
      return {
        ...state,
        isFetching: true,
        error: '',
      };
    default:
      return state;
  }
}
