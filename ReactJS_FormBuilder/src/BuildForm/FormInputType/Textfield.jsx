import React from "react";

const Textfield = ({ input, handleRenderInputSelect }) => (
  <a
    href="#!"
    key={input.inputId}
    id={input.inputId}
    className="list-group-item list-group-item-action"
    onClick={handleRenderInputSelect}
  >
    <div className="row">
      <div className="col-sm-5">
        <label>
          {input.properities.labelText}&nbsp;
          {input.properities.required ? (
            <span className="text-danger">*</span>
          ) : (
            <span></span>
          )}
          &nbsp;:&nbsp;&nbsp;&nbsp;
        </label>
      </div>

      <div className="col-sm-7">
        <input
          className="form-control"
          id={input.inputId}
          type="text"
          name={input.inputId + "_name"}
          disabled
        />
        <label className="instruction">
          #&nbsp;{input.properities.placeholderText}
        </label>
      </div>
    </div>
  </a>
);

export default Textfield;
