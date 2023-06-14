package com.diegonunez.chatkata.states

import com.diegonunez.chatkata.ui.screens.Contact

data class ContactsState(
    var isLoading: Boolean = true,
    var contacts: List<Contact> = emptyList(),
)
