import {
  PHOTO_LIST_BY_ADVERTISEMENT_REQUEST,
  PHOTO_LIST_BY_ADVERTISEMENT_RECEIVE,
  PHOTO_LIST_BY_ADVERTISEMENT_REJECT,
} from 'photo/actions/types';
import fetchGraphQL from 'shared/fetch/fetch-graphql';

function requestPhotos() {
  return {
    type: PHOTO_LIST_BY_ADVERTISEMENT_REQUEST,
  };
}

function receivePhotos(response) {
  return {
    type: PHOTO_LIST_BY_ADVERTISEMENT_RECEIVE,
    payload: {
      data: response.data,
    },
  };
}

function rejectPhotos(error) {
  return {
    type: PHOTO_LIST_BY_ADVERTISEMENT_REJECT,
    payload: error,
    error: true,
  };
}

function callFetchPhotosByAdvertisementId(advertisementId, language) {
  return fetchGraphQL(`
    query getPhotos($input: InputGetPhotosInput!, $language: String!) {
      photos(input: $input, language: $language) {
        id
        image
        advertisement {
          id
          name
          category {
            id
            name
          }
        }
        bus {
          id
          fleetPrefix
          fleetNumber
          licensePlateNumber
          busModel {
            id
            name
            brand {
              id
              name
            }
          }
          busCompanyName
        }
        busRoute {
          id
          routeNumber
        }
      }
    }`, {
      input: {
        advertisementId,
      },
      language
    });
}

export default function fetchPhotosByAdvertisementId(advertisementId, language) {
  return (dispatch, getState) => {
    const state = getState();
    const { isFetching } = state.photoListByAdvertisementReducer;

    if (isFetching) {
      return Promise.resolve();
    }

    dispatch(requestPhotos());
    return callFetchPhotosByAdvertisementId(advertisementId, language)
      .then(response => dispatch(receivePhotos(response)))
      .catch(error => dispatch(rejectPhotos(error)));
  };
}
