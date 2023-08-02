package net.jerrydev.baputils.guis

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import net.jerrydev.baputils.BapUtils
import net.jerrydev.baputils.core.BapConfig
import java.awt.Desktop
import java.net.URI

class BapGui : WindowScreen(ElementaVersion.V2) {
    init {
        UIText("BapUtils").childOf(super.window).constrain {
            x = CenterConstraint()
            y = PixelConstraint(45f)
            textScale = PixelConstraint(6.2f)
        }
        UIText("Options menu").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(10f)
            textScale = PixelConstraint(3.2f)
        }
        UIText("> Mod configuration <").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(80f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setActiveGui(BapConfig.gui())
        }.onMouseEnter {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.3f),
                )
            }
        }.onMouseLeave {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.2f),
                )
            }
        }
        UIText("> Help <").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(20f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            BapUtils.setActiveGui(null)
        }.onMouseEnter {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.3f),
                )
            }
        }.onMouseLeave {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.2f),
                )
            }
        }
        UIText("> Documentation <").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(20f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            Desktop.getDesktop().browse(URI("https://bap.jerrydev.net/"))
        }.onMouseEnter {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.3f),
                )
            }
        }.onMouseLeave {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.2f),
                )
            }
        }
        UIText("> Repository <").childOf(super.window).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(20f)
            textScale = PixelConstraint(2.2f)
        }.onMouseClick { (event) ->
            Desktop.getDesktop().browse(URI("https://github.com/AspectOfJerry/BapUtils/"))
        }.onMouseEnter {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.3f),
                )
            }
        }.onMouseLeave {
            animate {
                setTextScaleAnimation(
                    Animations.OUT_EXP,
                    0.3f,
                    PixelConstraint(2.2f),
                )
            }
        }
    }
}
