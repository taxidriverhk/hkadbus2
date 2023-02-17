import React from 'react';
import { Alert } from 'react-bootstrap';

import { strings } from 'shared/store/localization';

export default function NotFound() {
  return (
    <Alert bsStyle="danger">
      <p>
        {strings.notFoundMsg}
      </p>
    </Alert>
  );
}
