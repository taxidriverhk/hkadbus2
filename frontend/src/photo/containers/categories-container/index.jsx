import React, {
  useContext,
  useEffect,
} from 'react';
import {
  useDispatch,
  useSelector,
} from 'react-redux';

import { GlobalContext } from 'shared/components/global-context';
import { getCategories } from 'photo/selectors/categories';
import fetchCategories from 'photo/actions/categories';
import CategoriesView from 'photo/views/categories-view';

export default function CategoriesContainer() {
  const {
    categories,
    error,
    isFetching,
  } = useSelector(getCategories);
  const dispatch = useDispatch();
  const {
    language,
  } = useContext(GlobalContext);

  const dispatchFetchCategories = (targetLanguage) => {
    dispatch(fetchCategories(targetLanguage));
  };

  useEffect(() => {
    dispatchFetchCategories(language);
  }, []);

  useEffect(() => {
    dispatchFetchCategories(language);
  }, [language]);

  return (
    <CategoriesView
      categories={categories}
      isFetching={isFetching}
      error={error}
    />
  );
}
