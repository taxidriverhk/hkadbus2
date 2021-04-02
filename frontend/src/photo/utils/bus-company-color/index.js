export default function getColorByBusCompany(busCompany) {
  return {
    kmb: 'red',
    ctb: 'yellow',
    nwfb: 'green',
    cmb: 'light_blue',
  }[busCompany.toLowerCase()] || 'default';
}
