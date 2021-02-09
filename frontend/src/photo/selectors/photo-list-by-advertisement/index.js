import get from 'lodash/get';

export function getPhotosByAdvertisement(state) {
  const {
    advertisement,
    category,
    photos,
  } = state.photoListByAdvertisementReducer;

  const advertisementId = get(advertisement, 'id', 0);
  const photosById = get(photos, advertisementId, []);
  return {
    advertisement,
    category,
    photos: photosById,
  };
}
