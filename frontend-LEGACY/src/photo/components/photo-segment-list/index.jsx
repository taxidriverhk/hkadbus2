import PropTypes from 'prop-types';
import React from 'react';

import {
  ButtonGroup,
  Panel
} from 'react-bootstrap';

import PhotoSegment from 'photo/components/photo-segment';
import { segmentType } from 'photo/types';

const defaultProps = {
  title: '',
  segments: []
};
export const segmentGroupPropTypes = {
  title: PropTypes.string,
  segments: PropTypes.arrayOf(segmentType)
};

export default function PhotoSegmentList({
  title,
  segments
}) {
  return (
    <Panel>
      <Panel.Heading>
        {title}
      </Panel.Heading>
      <Panel.Body>
        <ButtonGroup vertical block>
          {
            segments.map(segment => (
              <PhotoSegment
                key={`model-${segment.id}`}
                segment={segment}
              />
            ))
          }
        </ButtonGroup>
      </Panel.Body>
    </Panel>
  );
}

PhotoSegmentList.defaultProps = defaultProps;
PhotoSegmentList.propTypes = segmentGroupPropTypes;
