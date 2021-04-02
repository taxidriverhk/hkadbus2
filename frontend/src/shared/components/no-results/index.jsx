import React from 'react';
import { Alert } from 'react-bootstrap';

import { strings } from 'shared/store/localization';

export default function NoResults() {
  return (
    <Alert bsStyle="warning">
      <p>
        {strings.noResults}
      </p>
    </Alert>
  );
}
