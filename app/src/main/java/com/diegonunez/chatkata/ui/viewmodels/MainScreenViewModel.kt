package com.diegonunez.chatkata.ui.viewmodels

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegonunez.chatkata.ui.screens.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import android.Manifest
import android.content.Intent
import android.provider.ContactsContract
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.diegonunez.chatkata.states.ContactsState
import com.diegonunez.chatkata.ui.activity.ChatActivity



@HiltViewModel
class MainScreenViewModel @Inject constructor(
) : ViewModel() {

    private val _state = mutableStateOf(ContactsState())
    val state: State<ContactsState> = _state

    private val requestCode = 1

    fun loadContacts(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            readContacts(context)
        } else {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                requestCode
            )
        }
    }

    private fun readContacts(context: Context){

        val contactIdCol = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
        val nameCol = ContactsContract.Contacts.DISPLAY_NAME
        val numberCol = ContactsContract.CommonDataKinds.Phone.NUMBER

        val projection = arrayOf(contactIdCol, nameCol, numberCol)

        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection, null, null, null
        )

        val contactIdIdx = cursor!!.getColumnIndex(contactIdCol)
        val nameIdx = cursor.getColumnIndex(nameCol)
        val numberIdx = cursor.getColumnIndex(numberCol)

        val contacts = mutableListOf<Contact>()

        while (cursor.moveToNext()) {
            val contactId = cursor.getString(contactIdIdx)
            val name = cursor.getString(nameIdx)
            val number = cursor.getString(numberIdx)

            val contact = Contact(contactId, name, number)
            contacts.add(contact)
        }

        cursor.close()

        _state.value = state.value.copy(
            contacts = contacts
        )
    }

    fun openChat(context: Context,contactId: String) {

        // Obtener el contacto correspondiente al ID
        val contact = state.value.contacts.find { it.id == contactId }


        // Verificar si se encontró el contacto
        if (contact != null) {
            // Aquí puedes implementar la lógica para abrir la ventana de chat
            // Puedes utilizar bibliotecas de navegación, iniciar una nueva actividad o fragmento, etc.

            // Ejemplo utilizando startActivity para abrir una nueva actividad de chat
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("contactId", contact.id)
            intent.putExtra("contactName", contact.name)
            intent.putExtra("contactPhoneNumber", contact.phoneNumber)
            context.startActivity(intent)
        }
    }

}