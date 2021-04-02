import React, {
  useContext,
  useEffect,
} from 'react';
import {
  useDispatch,
  useSelector,
} from 'react-redux';

import { GlobalContext } from 'shared/components/global-context';
import { getBusModels } from 'photo/selectors/bus-models';
import fetchBusModels from 'photo/actions/bus-models';
import BusModelListView from 'photo/views/bus-model-list-view';

export default function BusModelListContainer() {
  const {
    busBrandModelMap,
    error,
    isFetching,
  } = useSelector(getBusModels);
  const dispatch = useDispatch();
  const {
    language,
  } = useContext(GlobalContext);

  const dispatchFetchBusModels = (targetLanguage) => {
    dispatch(fetchBusModels(targetLanguage));
  };

  useEffect(() => {
    dispatchFetchBusModels(language);
  }, [language]);

  return (
    <BusModelListView
      busBrandModelMap={busBrandModelMap}
      isFetching={isFetching}
      error={error}
    />
  );
}
