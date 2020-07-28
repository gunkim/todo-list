import React from "react";
import styles from "./ListTemplate.module.scss";

function ListTemplate({ children }) {
  return <div className={styles.main}>{children}</div>;
}

export default ListTemplate;
