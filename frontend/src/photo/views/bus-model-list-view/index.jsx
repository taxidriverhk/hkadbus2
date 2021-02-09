import get from 'lodash/get';
import PropTypes from 'prop-types';
import React from 'react';

import {
  Breadcrumb,
  Link,
} from 'react-bootstrap';

import { strings } from 'shared/store/localization';
import PhotoSegmentList from 'photo/components/photo-segment-list';
import ErrorMessageBox from 'shared/components/error-message-box';
import Loader from 'shared/components/loader';

const propTypes = {
  busBrandModelMap: PropTypes.objectOf(
    PropTypes.arrayOf(PropTypes.shape({}))).isRequired,
  isFetching: PropTypes.bool.isRequired,
  error: PropTypes.string.isRequired
};

export default function BusModelListView({
  busBrandModelMap,
  isFetching,
  error
}) {
  return (
    <React.Fragment>
      <Breadcrumb>
        <Breadcrumb.Item
          componentClass={Link}
          to="/"
          href="/"
        >
          {strings.home}
        </Breadcrumb.Item>
        <Breadcrumb.Item active>
          {strings.categories}
        </Breadcrumb.Item>
      </Breadcrumb>
      {(() => {
        if (isFetching) {
          return <Loader />;
        } else if (error) {
          return <ErrorMessageBox />;
        } else {
          const busSegments = Object.entries(busBrandModelMap)
            .map(([busBrandName, busModels]) => ({
              id: `brand-${get(busModels, '[0]', 'unknown')}`,
              title: busBrandName,
              segments: busModels.map(({
                id,
                name,
                thumbnail,
              }) => ({
                id,
                photo: thumbnail,
                title: strings.formatString(strings.busModelName, busBrandName, name),
                description: '',
                link: `/models/${id}`,
              })),
            }));
          return busSegments.map(({
            id,
            title,
            segments,
          }) => (
            <PhotoSegmentList
              key={`brand-${id}`}
              title={title}
              segments={segments}
            />
          ));
        }
      })()}
    </React.Fragment>
  );
}

BusModelListView.propTypes = propTypes;
