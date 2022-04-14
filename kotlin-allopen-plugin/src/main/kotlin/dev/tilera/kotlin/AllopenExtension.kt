package dev.tilera.kotlin

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.extensions.DeclarationAttributeAltererExtension
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtModifierListOwner

class AllopenExtension: DeclarationAttributeAltererExtension {

    override fun refineDeclarationModality(
        modifierListOwner: KtModifierListOwner,
        declaration: DeclarationDescriptor?,
        containingDeclaration: DeclarationDescriptor?,
        currentModality: Modality,
        isImplicitModality: Boolean
    ): Modality? {
        if (currentModality != Modality.FINAL) {
            return null
        }
        
        if (declaration is ClassDescriptor || containingDeclaration != null) {
            return if (!isImplicitModality && modifierListOwner.hasModifier(KtTokens.FINAL_KEYWORD))
                Modality.FINAL
            else
                Modality.OPEN
        }

        return null
    }

}
