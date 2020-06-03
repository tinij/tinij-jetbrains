package com.tinij.intelij.plugin.eventListeners;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.tinij.intelij.plugin.Tinij;

public class TinijDocumentListener implements DocumentListener {

    private Tinij tinij;

    public TinijDocumentListener(Tinij tinij) {
        this.tinij = tinij;
    }

    @Override
    public void beforeDocumentChange(DocumentEvent documentEvent) {
    }

    @Override
    public void documentChanged(DocumentEvent documentEvent) {
        Document document = documentEvent.getDocument();
        FileDocumentManager instance = FileDocumentManager.getInstance();
        VirtualFile file = instance.getFile(document);
        tinij.addActivity(file, document, false);
    }
}
