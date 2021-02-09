import { 
  CATEGORIES_FETCH_REQUEST,
  CATEGORIES_FETCH_RECEIVE,
  CATEGORIES_FETCH_REJECT
} from 'photo/actions/types';

const initState = {
  error: '',
  isFetching: false,
  lastUpdated: null,
  categories: [],
};

export default function categoryListReducer(state = initState, action) {
  switch (action.type) {
    case CATEGORIES_FETCH_RECEIVE: {
      const { data } = action.payload;
      const { categories } = data;
      return {
        ...state,
        categories,
        isFetching: false,
        lastUpdated: Date.now(),
      };
    }
    case CATEGORIES_FETCH_REJECT:
      return {
        ...state,
        isFetching: false,
        error: action.payload.message,
      };
    case CATEGORIES_FETCH_REQUEST:
      return {
        ...state,
        isFetching: true,
        error: '',
      };
    default:
      return state;
  }
}
