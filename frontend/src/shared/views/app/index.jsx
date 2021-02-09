import PropTypes from 'prop-types';
import React, {
  useEffect,
  useState,
} from 'react';
import { Route, Switch } from 'react-router-dom';
import { Panel } from 'react-bootstrap';
import Cookies from 'universal-cookie';

import styles from 'shared/style/style.scss';
import * as Constants from 'shared/store/constants';
import { strings } from 'shared/store/localization';

import Home from 'shared/containers/home';
import Header from 'shared/components/header';
import { GlobalContext } from 'shared/components/global-context';
import NotFound from 'shared/components/not-found';
import AdvertisementsContainer from 'photo/containers/advertisements-container';
import BusModelListContainer from 'photo/containers/bus-model-list-container';
import CategoriesContainer from 'photo/containers/categories-container';
import PhotoDetailsContainer from 'photo/containers/photo-details-container';
import PhotoListByAdvertisementContainer from 'photo/containers/photo-list-by-advertisement-container';

const DEFAULT_LANGUAGE = 'en_us';
const NUM_OF_HEADER_IMAGES = 5;

const headerImages = Array(NUM_OF_HEADER_IMAGES).fill(0)
  .map((_, index) => `${Constants.IMAGE_BASE_PATH}header-${index + 1}.jpg`);
const cookies = new Cookies();

const propTypes = {
  history: PropTypes.object.isRequired,
  location: PropTypes.object.isRequired
};

const getDefaultLanguage = () => {
  const savedLanguage = cookies.get('defaultLanguage');
  if (savedLanguage) {
    strings.setLanguage(savedLanguage);
    return savedLanguage;
  }
  return DEFAULT_LANGUAGE;
};

export default function App(props) {
  const [
    headerImg,
    setHeaderImg,
  ] = useState(headerImages[0]);
  const [
    globalContext,
    setGlobalContext,
  ] = useState({
    language: getDefaultLanguage(),
  });

  const changeLanguage = (targetLanguage) => () => {
    if (language === targetLanguage) {
      return;
    }

    let languageToUse = targetLanguage;
    const langs = strings.getAvailableLanguages();
    if (langs.indexOf(targetLanguage) < 0) {
      languageToUse = DEFAULT_LANGUAGE;
    }

    strings.setLanguage(languageToUse);
    cookies.set('defaultLanguage', languageToUse);
    setGlobalContext({
      ...globalContext,
      language: languageToUse,
    });
  }

  const updateHeaderImage = () => {
    const randomIndex = Math.floor(Math.random() * Math.floor(NUM_OF_HEADER_IMAGES));
    setHeaderImg(headerImages[randomIndex]);
  };

  useEffect(() => {
    const { history } = props;
    // Bind listener for firing the handler when the route gets changed
    history.listen(updateHeaderImage);
    updateHeaderImage();
  });

  const { language } = globalContext;

  return (
    <GlobalContext.Provider value={globalContext}>
      <div className="container">
        <Header
          headerImg={headerImg}
          language={language}
          onChangeLanguage={changeLanguage}
        />
        <Panel className={styles['body-container']}>
          <Switch>
            <Route
              component={Home}
              exact
              path="/"
            />
            <Route
              component={CategoriesContainer}
              exact
              path='/categories'
            />
            <Route
              component={AdvertisementsContainer}
              exact
              path='/categories/:id'
            />
            <Route
              component={PhotoListByAdvertisementContainer}
              exact
              path='/advertisements/:id'
            />
            <Route
              component={PhotoDetailsContainer}
              exact
              path='/photos/:id'
            />
            <Route
              component={BusModelListContainer}
              exact
              path='/models'
            />
            <Route component={NotFound} />
          </Switch>
        </Panel>
        <Panel.Footer className={styles['body-footer']}>
                  Created by Alex Leung &copy; 2018
        </Panel.Footer>
      </div>
    </GlobalContext.Provider>
  );
}

App.propTypes = propTypes;
