import React from "react";
import styles from "./Template.module.scss";

function ListTemplate({ form, children, foot }) {
  return (
    <div className={styles.main}>
      {form}
      {children}
      {foot}
    </div>
  );
}

export default ListTemplate;
