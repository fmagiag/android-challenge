package com.magiag.androidchallenge

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.Toast

fun convertHTML(html: String): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(html)
    }
}

fun showDeleteToast(context: Context, valueMessage: String) {
    Toast.makeText(context,
            StringBuilder(valueMessage).append(" ")
                    .append(context.getString(R.string.dialog_shows_toast_message)),
            Toast.LENGTH_SHORT).show()
}

fun showSaveToast(context: Context, valueMessage: String) {
    Toast.makeText(context,
            StringBuilder(valueMessage).append(" ")
                    .append(context.getString(R.string.dialog_favorites_toast_message)),
            Toast.LENGTH_SHORT).show()
}

fun deleteDialog(context: Context,
                 onClickPositive: DialogInterface.OnClickListener): AlertDialog.Builder {
    return AlertDialog.Builder(context, R.style.AlertDialogStyle)
            .setTitle(context.getString(R.string.dialog_favorites_title))
            .setMessage(context.getString(R.string.dialog_favorites_message))
            .setPositiveButton(context.getString(
                    R.string.dialog_favorites_positive_button), onClickPositive)
            .setNegativeButton(context.getString(
                    R.string.dialog_favorites_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
}

fun getShowsErrorDialog(context: Context,
                 onClickPositive: DialogInterface.OnClickListener): AlertDialog.Builder {
    return AlertDialog.Builder(context, R.style.AlertDialogStyle)
            .setTitle(context.getString(R.string.dialog_get_shows_error_title))
            .setMessage(context.getString(R.string.dialog_get_shows_error_message))
            .setPositiveButton(context.getString(
                    R.string.dialog_get_shows_error_positive_button), onClickPositive)
            .setNegativeButton(context.getString(
                    R.string.dialog_get_shows_error_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
}

fun savingErrorDialog(context: Context,
                        onClickPositive: DialogInterface.OnClickListener): AlertDialog.Builder {
    return AlertDialog.Builder(context, R.style.AlertDialogStyle)
            .setTitle(context.getString(R.string.dialog_save_show_error_title))
            .setMessage(context.getString(R.string.dialog_save_show_error_message))
            .setPositiveButton(context.getString(
                    R.string.dialog_save_show_error_positive_button), onClickPositive)
            .setNegativeButton(context.getString(
                    R.string.dialog_save_show_error_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
}

fun deletingErrorDialog(context: Context,
                      onClickPositive: DialogInterface.OnClickListener): AlertDialog.Builder {
    return AlertDialog.Builder(context, R.style.AlertDialogStyle)
            .setTitle(context.getString(R.string.dialog_delete_show_error_title))
            .setMessage(context.getString(R.string.dialog_delete_show_error_message))
            .setPositiveButton(context.getString(
                    R.string.dialog_delete_show_error_positive_button), onClickPositive)
            .setNegativeButton(context.getString(
                    R.string.dialog_delete_show_error_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
}

