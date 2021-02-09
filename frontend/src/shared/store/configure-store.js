import { routerReducer, routerMiddleware } from 'react-router-redux';
import { applyMiddleware, createStore, combineReducers } from 'redux';
import { createLogger } from 'redux-logger';
import thunk from 'redux-thunk';
import rootReducer from 'shared/reducers';
import { createBrowserHistory } from 'history';

const history = createBrowserHistory();
const logger = createLogger();

export default function configureStore(preloadedState = {}) {
  const store = createStore(
    combineReducers({
      ...rootReducer,
      router: routerReducer
    }),
    preloadedState,
    applyMiddleware(
      routerMiddleware(history),
      thunk,
      logger
    )
  );

  return { history, store };
}