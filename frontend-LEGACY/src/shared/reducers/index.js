import advertisementListReducer from 'photo/reducers/advertisements';
import busModelListReducer from 'photo/reducers/bus-models';
import categoryListReducer from 'photo/reducers/categories';
import photoDetailReducer from 'photo/reducers/photo';
import searchResultReducer from 'photo/reducers/search';
import updateListReducer from 'shared/reducers/updates';

const rootReducer = {
  advertisementListReducer,
  busModelListReducer,
  categoryListReducer,
  photoDetailReducer,
  searchResultReducer,
  updateListReducer,
};
export default rootReducer;
