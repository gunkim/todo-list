import React from "react";
import styles from "./Template.module.scss";

function ListTemplate({ children }) {
  return <div className={styles.main}>{children}</div>;
}

export default ListTemplate;
