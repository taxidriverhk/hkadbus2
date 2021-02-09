import groupBy from 'lodash/groupBy';

import {
  BUS_MODELS_REQUEST,
  BUS_MODELS_RECEIVE,
  BUS_MODELS_REJECT,
} from 'photo/actions/types';

const initState = {
  error: '',
  isFetching: false,
  lastUpdated: null,
  busBrandModelMap: {},
};

export default function busModelListReducer(state = initState, action) {
  switch (action.type) {
    case BUS_MODELS_RECEIVE: {
      const { data } = action.payload;
      const { busModels } = data;

      const busBrandModelMap = groupBy(busModels, 'busBrandName');

      return {
        ...state,
        busBrandModelMap,
        isFetching: false,
        lastUpdated: Date.now(),
      };
    }
    case BUS_MODELS_REJECT:
      return {
        ...state,
        isFetching: false,
        error: action.payload.message,
      };
    case BUS_MODELS_REQUEST:
      return {
        ...state,
        isFetching: true,
        error: '',
      };
    default:
      return state;
  }
}
