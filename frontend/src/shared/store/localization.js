import LocalizedStrings from 'react-localization';

export let strings = new LocalizedStrings({
  'en_us': {
    // Shared layout
    title: 'Hong Kong Advertised Bus Photo Database v2.0',
    home: 'Home',
    categories: 'Categories',
    models: 'Bus Models',
        
    // Home page
    welcome: 'Welcome',
    welcomeMessage: 'This page collects photos of buses of different full-body advertisements taken by bus fans.',
    recentUpdates: 'Recent Updates',
    noRecentUpdates: 'No recent updates available',

    // Categories
    numAdvertisements: 'Advertisements: {0}',
    numPhotos: 'Photos: {0}',

    // Photo Details
    photoDetails: 'Photo Details',
    busModel: 'Model',
    busModelName: '{0} {1}',
    fleetPrefix: 'Prefix (ex. 3AV)',
    fleetSuffix: 'Number (ex. 102)',
    fleetNumber: 'Fleet Number',
    licensePlateNumber: 'Licence Plate Number',
    busCompany: 'Bus Company',
    routeNumber: 'Route',
    uploadedBy: 'Uploaded By',
    dateUploaded: 'Date Uploaded',
    relatedModel: 'Related photos of same model',
    relatedFleet: 'Related photos of same fleet',
    relatedBus: 'Related photos of same bus',
    relatedRoute: 'Related photos of same router number',

    // Search Photos
    search: 'Search',
    general: 'Keywords (separate by commas or space)',

    // Messages
    loadingTitle: 'Loading',
    loadingMsg: 'Content is being loaded, please wait.',
    errorTitle: 'Error',
    errorMsg: 'Error occurred when trying to load the contents, please try again later.',
    noResults: 'No results found, please refine the query and try search again.',
    notFoundMsg: 'Page not found, please check the URL and try again.'
  },
  'zh_hk': {
    // Shared layout
    title: '香港廣告巴士相片資料庫 v2.0',
    home: '主頁',
    categories: '分類',
    models: '巴士型號',
        
    // Home page
    welcome: '歡迎瀏覽',
    welcomeMessage: '本網頁旨在收集各個巴士迷拍攝之全車身廣告巴士相片。',
    recentUpdates: '最近更新',
    noRecentUpdates: '沒有任何最近更新',

    // Categories
    numAdvertisements: '廣告數目: {0}',
    numPhotos: '相片數目: {0}',

    // Photo Details
    photoDetails: '詳細資料',
    busModel: '型號',
    busModelName: '{0}{1}',
    fleetPrefix: '前綴 (例: 3AV)',
    fleetSuffix: '後綴 (例: 102)',
    fleetNumber: '車隊編號',
    licensePlateNumber: '車牌號碼',
    busCompany: '巴士公司',
    routeNumber: '路線編號',
    dateUploaded: '上傳日期',
    uploadedBy: '上傳者',
    relatedModel: '相同車型',
    relatedFleet: '相同車隊',
    relatedBus: '相同巴士',
    relatedRoute: '相同路線',

    // Search Photos
    search: '搜尋',
    general: '關鍵字 (以逗號或空格分隔)',

    // Messages
    loadingTitle: '載入中',
    loadingMsg: '正在載入中, 請稍候。',
    errorTitle: '錯誤',
    errorMsg: '發生錯誤, 請稍後再試。',
    noResults: '找不到任何結果, 請修正搜尋字眼並再試。',
    notFoundMsg: '找不到網頁, 請檢查網址再試。'
  }
});