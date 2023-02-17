import React, {
  useContext,
  useEffect,
} from 'react';
import {
  useDispatch,
  useSelector,
} from 'react-redux';
import { useHistory } from 'react-router-dom';

import { GlobalContext } from 'shared/components/global-context';
import { getSearchResults } from 'photo/selectors/search-results';
import searchPhotos from 'photo/actions/search';
import SearchPhotosView from 'photo/views/search-photos-view';
import SearchQueryUtil from 'photo/utils/search-query-util';

export default function SearchPhotosContainer(props) {
  const {
    location: {
      search: queryParams,
    },
  } = props;
  const {
    error,
    filter,
    isFetching,
    lastSortKey,
    sort,
    results,
  } = useSelector(getSearchResults);
  const history = useHistory();
  const dispatch = useDispatch();
  const {
    language,
  } = useContext(GlobalContext);

  const dispatchSearchPhotos = (
    nextQueryParams,
    nextSort,
    nextSortKey,
    nextLanguage,
  ) => {
    dispatch(searchPhotos(
      nextQueryParams,
      nextSort,
      nextSortKey,
      nextLanguage,
    ));
  };
  useEffect(() => {
    dispatchSearchPhotos(
      queryParams,
      sort,
      lastSortKey,
      language,
    );
  }, [queryParams, language]);

  const handleFilterUpdate = (updatedFilter) => {
    const urlParams = SearchQueryUtil.convertToQueryParams(updatedFilter, sort);
    history.push(`/search?${urlParams}`);
  };

  return (
    <SearchPhotosView
      isFetching={isFetching}
      error={error}
      filter={filter}
      onFilter={handleFilterUpdate}
      results={results}
    />
  );
}
