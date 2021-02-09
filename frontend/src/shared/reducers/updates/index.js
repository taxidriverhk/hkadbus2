import { 
  UPDATES_REQUEST,
  UPDATES_RECEIVE,
  UPDATES_REJECT,
} from 'shared/actions/types';

const initState = {
  error: null,
  isFetching: false,
  lastUpdated: null,
  updates: [],
};

export default function updateListReducer(state = initState, action) {
  switch (action.type) {
    case UPDATES_RECEIVE: {
      const { data } = action.payload;
      const { updates } = data;
      return {
        ...state,
        updates,
        isFetching: false,
        lastUpdated: Date.now(),
      };
    }
    case UPDATES_REJECT:
      return {
        ...state,
        isFetching: false,
        error: action.payload.message
      };
    case UPDATES_REQUEST:
      return {
        ...state,
        isFetching: true
      };
    default:
      return state;
  }
}
