import {
  useDispatch,
  useSelector,
} from 'react-redux';
import React, {
  useContext,
  useEffect,
} from 'react';

import { GlobalContext } from 'shared/components/global-context';
import { getPhoto } from 'photo/selectors/photo';
import fetchPhoto from 'photo/actions/photo';
import PhotoDetailsView from 'photo/views/photo-details-view';

export default function PhotoDetailsContainer(props) {
  const {
    photo,
    isFetching,
    error,
  } = useSelector(getPhoto);
  const { id } = props.match.params;
  const dispatch = useDispatch();
  const {
    language,
  } = useContext(GlobalContext);

  const dispatchFetchPhoto = (targetId, targetLanguage) => {
    dispatch(fetchPhoto(targetId, targetLanguage));
  };

  useEffect(() => {
    dispatchFetchPhoto(id, language);
  }, []);
  useEffect(() => {
    dispatchFetchPhoto(id, language);
  }, [language]);

  return (
    <PhotoDetailsView
      photo={photo}
      isFetching={isFetching}
      error={error}
    />
  );
}
