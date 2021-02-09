import React from 'react';
import FontAwesome from 'react-fontawesome';

export default function Loader() {
  return (
    <div className="loader">
      <FontAwesome
        name="spinner"
        spin
      />
    </div>
  );
}
