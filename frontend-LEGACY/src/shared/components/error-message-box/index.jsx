import PropTypes from 'prop-types';
import React from 'react';
import { Alert } from 'react-bootstrap';

import { strings } from 'shared/store/localization';

const defaultProps = {
  error: '',
};

const propTypes = {
  error: PropTypes.string.isRequired
};

export default function ErrorMessageBox(props) {
  const { error } = props;
  return (
    <Alert bsStyle="danger">
      <p>
        {(() => {
          if (error.includes('404')) {
            return strings.notFoundMsg;
          }
          return strings.errorMsg;
        })()}
      </p>
    </Alert>
  );
}

ErrorMessageBox.defaultProps = defaultProps;
ErrorMessageBox.propTypes = propTypes;
