const LOCAL_BASE_URL = 'http://localhost:8080/maple/order';
const LOCAL_HOME_URL = 'http://localhost:8080/maple';

const IP_BASE_URL = 'http://192.168.75.71:8080/maple/order';
const IP_HOME_URL = 'http://192.168.75.71:8080/maple';

// 환경에 따라 URL을 선택
const isLocal = window.location.hostname === 'localhost';

export const BASE_URL = isLocal ? LOCAL_BASE_URL : IP_BASE_URL;
export const HOME_URL = isLocal ? LOCAL_HOME_URL : IP_HOME_URL;