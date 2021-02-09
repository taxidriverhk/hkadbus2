import advertisementListReducer from 'photo/reducers/advertisements';
import busModelListReducer from 'photo/reducers/bus-models';
import categoryListReducer from 'photo/reducers/categories';
import photoDetailReducer from 'photo/reducers/photo';
import photoListByAdvertisementReducer from 'photo/reducers/photo-list-by-advertisement';
import updateListReducer from 'shared/reducers/updates';

const rootReducer = {
  advertisementListReducer,
  busModelListReducer,
  categoryListReducer,
  photoDetailReducer,
  photoListByAdvertisementReducer,
  updateListReducer,
};
export default rootReducer;
