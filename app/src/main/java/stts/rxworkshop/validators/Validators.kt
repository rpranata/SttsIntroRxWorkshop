package stts.rxworkshop.validators

import java.util.regex.Pattern

/**
 * Interface to validate a string value
 *
 * Created by RenD on 21/1/18.
 */
interface Validator {
  fun validate(value: CharSequence): Boolean
}

object EmailValidator : Validator {
  override fun validate(value: CharSequence): Boolean =
    value.matches(
      "^[+\\w\\.\\-\\~\\#\\$\\%\\^\\&\\*\\/']+@[a-zA-Z0-9][.a-zA-Z0-9-]*(\\.[a-zA-Z]{2,20})+$"
        .toRegex()
    )
}

object AlphanumericValidator : Validator {
  override fun validate(value: CharSequence): Boolean {
    val number = Pattern.compile("[0-9]+")
    val letter = Pattern.compile("[A-Za-z]+")
    return number.matcher(value).find() && letter.matcher(value).find()
  }
}
