import React, {
  useContext,
  useEffect,
} from 'react';
import {
  useDispatch,
  useSelector,
} from 'react-redux';

import styles from 'shared/style/style.scss';
import { strings } from 'shared/store/localization';
import { getUpdates } from 'shared/selectors/updates';
import fetchUpdates from 'shared/actions/updates';
import { GlobalContext } from 'shared/components/global-context';
import UpdatesFeed from 'shared/components/updates-feed';

export default function Home() {
  const {
    error,
    isFetching,
    updates,
  } = useSelector(getUpdates);
  const dispatch = useDispatch();
  const {
    language,
  } = useContext(GlobalContext);

  const dispatchFetchUpdates = (targetLanguage) => {
    dispatch(fetchUpdates(targetLanguage));
  };

  useEffect(() => {
    dispatchFetchUpdates(language);
  }, []);

  useEffect(() => {
    dispatchFetchUpdates(language);
  }, [language]);

  return (
    <div>
      <div className={styles['home-section']}>
        <h3>{strings.welcome}</h3>
        <p>{strings.welcomeMessage}</p>
      </div>
      <div>
        <h4>{strings.recentUpdates}</h4>
        <UpdatesFeed
          error={error}
          isFetching={isFetching}
          updates={updates}
        />
      </div>
    </div>
  );
}
