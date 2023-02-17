import React, {
  useContext,
  useEffect,
} from 'react';
import {
  useDispatch,
  useSelector,
} from 'react-redux';

import { GlobalContext } from 'shared/components/global-context';
import { getAdvertisements } from 'photo/selectors/advertisements';
import fetchAdvertisements from 'photo/actions/advertisements';
import AdvertisementsView from 'photo/views/advertisements-view';

export default function AdvertisementsContainer(props) {
  const {
    advertisements,
    category,
    isFetching,
    error,
  } = useSelector(getAdvertisements);
  const { id } = props.match.params;
  const dispatch = useDispatch();
  const {
    language,
  } = useContext(GlobalContext);

  const dispatchFetchAdvertisements = (targetId, targetLanguage) => {
    dispatch(fetchAdvertisements(targetId, targetLanguage));
  };

  useEffect(() => {
    dispatchFetchAdvertisements(id, language);
  }, [language]);

  return (
    <AdvertisementsView
      advertisements={advertisements}
      category={category}
      isFetching={isFetching}
      error={error}
    />
  );
}
