export const createStateUtils = {
  initial: (initialData = null) => ({
    loading: false,
    data: initialData,
    error: null,
  }),
  request: () => ({
    loading: true,
    error: null,
  }),
  success: (payload) => ({
    loading: false,
    data: payload,
    error: false,
  }),
  error: (error) => ({
    loading: false,
    data: null,
    error: error,
  }),
};
export const createReducer = (type) => {
  const [REQUEST, SUCCESS, ERROR] = [type, `${type}_SUCCESS`, `${type}_ERROR`];
  return (state, action) => {
    switch (action.type) {
      case REQUEST:
        return {
          ...state,
          ...createStateUtils.request(),
        };
      case SUCCESS:
        return {
          ...state,
          ...createStateUtils.success(action.payload),
        };
      case ERROR:
        return {
          ...state,
          ...createStateUtils.error(action.payload),
        };
      default:
        return state;
    }
  };
};
