package com.rdpp.komorebi.listener

import com.rdpp.komorebi.model.Article
import com.rdpp.komorebi.model.Workshop

interface WorkshopEventListener {

    fun shareWorkshop(workshop: Workshop) {}

    fun viewWorkshop(workshop: Workshop) {}
}