package com.breaktime.gp_core

import com.breaktime.gp_core.core.factory.TGFactory
import com.breaktime.gp_core.core.io.base.TGFileFormat
import com.breaktime.gp_core.core.io.base.TGFileFormatManager
import com.breaktime.gp_core.core.io.base.TGFileFormatUtils
import com.breaktime.gp_core.core.io.base.TGSongPersistenceHelper.ATTRIBUTE_FORMAT_CODE
import com.breaktime.gp_core.core.io.base.TGSongReaderHandle
import com.breaktime.gp_core.core.io.base.TGSongStreamContext
import com.breaktime.gp_core.core.io.base.TGSongWriterHandle
import com.breaktime.gp_core.core.util.TGContext
import com.breaktime.gp_core.gtp.GP1InputStream
import com.breaktime.gp_core.gtp.GP1InputStreamPlugin
import com.breaktime.gp_core.gtp.GP2InputStream
import com.breaktime.gp_core.gtp.GP2InputStreamPlugin
import com.breaktime.gp_core.gtp.GP3InputStream
import com.breaktime.gp_core.gtp.GP3InputStreamPlugin
import com.breaktime.gp_core.gtp.GP4InputStream
import com.breaktime.gp_core.gtp.GP4InputStreamPlugin
import com.breaktime.gp_core.gtp.GP5InputStream
import com.breaktime.gp_core.gtp.GP5InputStreamPlugin
import com.breaktime.gp_core.gtp.GTPFileFormatDetector
import com.breaktime.gp_core.gtp.GTPSettings
import com.breaktime.gp_core.pdf.PDFSongWriter
import java.io.File

object GpPdfConverter {
    private val tgContext = TGContext()
    private val formatManager = TGFileFormatManager.getInstance(tgContext).apply {
        addReader(com.breaktime.gp_core.gpx.v6.GPXInputStream())
        addReader(com.breaktime.gp_core.gpx.v7.GPXInputStream())
        addReader(GP1InputStream(GTPSettings()))
        addReader(GP2InputStream(GTPSettings()))
        addReader(GP3InputStream(GTPSettings()))
        addReader(GP4InputStream(GTPSettings()))
        addReader(GP5InputStream(GTPSettings()))
        addFileFormatDetector(GP1InputStreamPlugin().createFileFormatDetector(tgContext))
        addFileFormatDetector(GP2InputStreamPlugin().createFileFormatDetector(tgContext))
        addFileFormatDetector(GP3InputStreamPlugin().createFileFormatDetector(tgContext))
        addFileFormatDetector(GP4InputStreamPlugin().createFileFormatDetector(tgContext))
        addFileFormatDetector(GP5InputStreamPlugin().createFileFormatDetector(tgContext))
        addFileFormatDetector(com.breaktime.gp_core.gpx.v6.GPXFileFormatDetector())
        addFileFormatDetector(com.breaktime.gp_core.gpx.v7.GPXFileFormatDetector())
    }
    private val factory = TGFactory()

    fun convertFileToPdf(songFile: File, toSrc: File) {
        val fileFormatCode = TGFileFormatUtils.getFileFormatCode(songFile.absolutePath)
        val handle = readSong(songFile, fileFormatCode)
        writePdf(handle, toSrc)
    }

    private fun readSong(songFile: File, fileFormatCode: String) = TGSongReaderHandle().apply {
        inputStream = songFile.inputStream()
        factory = this@GpPdfConverter.factory
        context = TGSongStreamContext()
        context.setAttribute(ATTRIBUTE_FORMAT_CODE, fileFormatCode)
        formatManager.read(this)
    }

    private fun writePdf(handle: TGSongReaderHandle, toSrc: File) {
        val writerHandle = TGSongWriterHandle().apply {
            context = TGSongStreamContext()
            song = handle.song
            format = handle.format
            factory = this@GpPdfConverter.factory
            outputStream = toSrc.outputStream()
        }
        val writer = PDFSongWriter(TGContext())
        writer.write(writerHandle)
    }
}